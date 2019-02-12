package xyz.nietongxue.serviceql

inline fun <reified T> bean():T?{
    return ApplicationBootConfiguration.context.getBean(T::class.java)
}