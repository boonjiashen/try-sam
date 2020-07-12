package me.jiashenb.trysam;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.lambda.runtime.events.SQSEvent;
import com.amazonaws.xray.AWSXRay;
import com.amazonaws.xray.entities.Segment;
import com.amazonaws.xray.entities.TraceHeader;

public class ReceiverFromQueue implements RequestHandler<SQSEvent, Void> {

    private String AWS_TRACE_HEADER = "AWSTraceHeader";

    @Override
    public Void handleRequest(SQSEvent event, Context context) {
        event.getRecords().stream().forEach(this::instrumentXRay);

        event.getRecords()
                .stream()
                .forEach(message -> System.out.println(message.getBody()));

        return null;
    }

    private void instrumentXRay(SQSEvent.SQSMessage message) {
        String traceHeaderStr = message.getAttributes().get(AWS_TRACE_HEADER);
        if (traceHeaderStr == null) {
            return;
        }

        // Recover the trace context from the trace header
        // See:
        // https://docs.aws.amazon.com/xray/latest/devguide/xray-services-sqs.html#xray-services-sqs-retrieving
        TraceHeader traceHeader = TraceHeader.fromString(traceHeaderStr);
        Segment segment = AWSXRay.getCurrentSegment();
        segment.setTraceId(traceHeader.getRootTraceId());
        segment.setParentId(traceHeader.getParentId());
        segment.setSampled(traceHeader.getSampled().equals(TraceHeader.SampleDecision.SAMPLED));
    }
}
