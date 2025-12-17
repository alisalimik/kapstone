const path = require('path');

module.exports = function (config) {
    console.log("DEBUG KARMA SCRIPT: CWD=" + process.cwd());
    console.log("DEBUG KARMA SCRIPT: __dirname=" + __dirname);

    // Hardcoded absolute path for verification and to bypass relative path issues in KGP temp dir
    const projectRoot = '/Users/alisalimi/AndroidStudioProjects/kapstone';

    // Ensure order: polyfill first, then capstone lib, then test runner (handled by KGP)

    config.files.unshift({
        pattern: path.resolve(projectRoot, 'library/browser-polyfill.js'),
        included: true,
        served: true,
        watched: false
    });

    // capstone.js MUST be loaded before tests that use it
    config.files.unshift({
        pattern: path.resolve(projectRoot, 'library/src/webMain/resources/capstone.js'),
        included: true,
        served: true,
        watched: false
    });

    // capstone.wasm must be served but not included as script
    config.files.push({
        pattern: path.resolve(projectRoot, 'library/src/webMain/resources/capstone.wasm'),
        included: false,
        served: true,
        watched: false
    });
};
