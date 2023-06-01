package it.polito.wa2.g11.ticketing.observation

import io.micrometer.observation.Observation
import io.micrometer.observation.ObservationHandler
import io.micrometer.observation.aop.ObservedAspect
import org.springframework.stereotype.Component

@Component
class AbstractObserveAroundMethodHandler : AbstractLogAspect(),
    ObservationHandler<ObservedAspect.ObservedAspectContext> {

    override fun onStart(context: ObservedAspect.ObservedAspectContext) {
        val joinPoint = context.proceedingJoinPoint
        super.logBefore(joinPoint)
    }

    override fun onStop(context: ObservedAspect.ObservedAspectContext) {
        val joinPoint = context.proceedingJoinPoint
        super.logAfter(joinPoint)
    }

    override fun supportsContext(context: Observation.Context): Boolean {
        return context is ObservedAspect.ObservedAspectContext
    }
}