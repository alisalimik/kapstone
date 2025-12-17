package ir.alisalimik.kapstone.internal

@Target(
    AnnotationTarget.CLASS,
    AnnotationTarget.PROPERTY,
    AnnotationTarget.FUNCTION,
    AnnotationTarget.FILE)
@Retention(AnnotationRetention.BINARY)
expect annotation class ExportedApi()
