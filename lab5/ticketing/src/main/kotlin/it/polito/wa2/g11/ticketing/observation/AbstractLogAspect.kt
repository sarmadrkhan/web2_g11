package it.polito.wa2.g11.ticketing.observation

import org.aspectj.lang.ProceedingJoinPoint
import org.slf4j.LoggerFactory

open class AbstractLogAspect {

    fun logBefore(joinPoint: ProceedingJoinPoint) {
        val logInfo = getLogInfo(joinPoint)
        val log = LoggerFactory.getLogger(logInfo.declaringType)
        log.info(
            "[{}.{}] start ({})", logInfo.className,
            logInfo.annotatedMethodName, logInfo.args
        )
    }

    private fun getLogInfo(joinPoint: ProceedingJoinPoint): LogInfo {
        val signature = joinPoint.signature
        val declaringType = signature.declaringType
        val className = declaringType.simpleName
        val annotatedMethodName = signature.name
        val args = joinPoint.args
        return LogInfo(declaringType, className, annotatedMethodName, args)
    }

    fun logAfter(joinPoint: ProceedingJoinPoint) {
        val logInfo = getLogInfo(joinPoint)
        val log = LoggerFactory.getLogger(logInfo.declaringType)
        log.info("[{}.{}] end", logInfo.className, logInfo.annotatedMethodName)
    }

    class LogInfo(
        val declaringType: Class<*>?,
        val className: String,
        val annotatedMethodName: String,
        val args: Array<Any>
    ) {
    }
}