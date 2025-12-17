import fs from 'fs';
import path from 'path';
import { fileURLToPath } from 'url';
import { WASI } from 'wasi'; // Node.js built-in WASI

const __dirname = path.dirname(fileURLToPath(import.meta.url));
const wasmPath = path.resolve(__dirname, 'capstone-wasi.wasm');

// 1. Setup WASI for Capstone
const wasi = new WASI({
    version: 'preview1',
    args: [],
    env: process.env,
    preopens: { '/ ': __dirname } // Map current dir?
});

// 2. Load and Instantiate
const buffer = fs.readFileSync(wasmPath);
const wasmModule = new WebAssembly.Module(buffer);

const importObject = {
    // Provide WASI imports
    wasi_snapshot_preview1: wasi.wasiImport,

    // Provide Emscripten/Env imports
    env: {
        memory: new WebAssembly.Memory({ initial: 256 }),
        table: new WebAssembly.Table({ initial: 0, element: 'anyfunc' }),
        __memory_base: 0,
        __table_base: 0,
        abort: () => { throw new Error('Abort called from Capstone'); },
        // Add minimal emscripten syscalls if needed (capstone might use them)
        emscripten_notify_memory_growth: () => { },
    }
};

const instance = new WebAssembly.Instance(wasmModule, importObject);

// Initialize WASI (if capstone has a _start, otherwise we assume it's a library)
// If it's a reactor/library, no need to call start usually, or use .initialize()
// wasi.start(instance);
// Note: wasi.start calls _start. Capstone is likely a library.

// 3. Export Helper Functions (Bridge)

const e = instance.exports;

// Helper to find export with or without underscore
function get(name) {
    // try name as is
    if (e[name]) return e[name];
    // try removing leading underscore if present
    if (name.startsWith('_') && e[name.substring(1)]) return e[name.substring(1)];
    // try adding leading underscore
    if (!name.startsWith('_') && e['_' + name]) return e['_' + name];

    console.warn(`WARNING: Export '${name}' not found in capstone-wasi.wasm exports:`, Object.keys(e).filter(k => k.includes('cs_')));
    return undefined;
}

export const _malloc = get('_malloc') || get('malloc');
export const _free = get('_free') || get('free');

// Capstone functions
export const _cs_open = get('_cs_open');
export const _cs_close = get('_cs_close');
export const _cs_option = get('_cs_option');
export const _cs_errno = get('_cs_errno');
export const _cs_strerror = get('_cs_strerror');
export const _cs_disasm = get('_cs_disasm');
export const _cs_free = get('_cs_free');
export const _cs_reg_name = get('_cs_reg_name');
export const _cs_insn_name = get('_cs_insn_name');
export const _cs_group_name = get('_cs_group_name');
export const _cs_insn_group = get('_cs_insn_group');
export const _cs_reg_read = get('_cs_reg_read');
export const _cs_reg_write = get('_cs_reg_write');
export const _cs_op_count = get('_cs_op_count');
export const _cs_op_index = get('_cs_op_index');
export const _cs_regs_access = get('_cs_regs_access');
export const _cs_arch_register_x86 = get('_cs_arch_register_x86');
export const _cs_arch_register_arm = get('_cs_arch_register_arm');
export const _cs_arch_register_aarch64 = get('_cs_arch_register_aarch64');
export const _cs_version = get('_cs_version');
export const _cs_support = get('_cs_support');
// Also cs_malloc might be needed if _malloc falls back to it?
// strings showed cs_malloc, check if that is used for general allocation or just internal. Use standard malloc for generic.

// 4. Memory Accessors for Foreign Memory
const mem = e.memory || importObject.env.memory;

export function bridge_read_int8(addr) {
    return new Int8Array(mem.buffer)[addr];
}

export function bridge_read_int16(addr) {
    return new Int16Array(mem.buffer)[addr >> 1];
}

export function bridge_read_int32(addr) {
    return new Int32Array(mem.buffer)[addr >> 2];
}

export function bridge_write_int8(addr, val) {
    new Int8Array(mem.buffer)[addr] = val;
}
