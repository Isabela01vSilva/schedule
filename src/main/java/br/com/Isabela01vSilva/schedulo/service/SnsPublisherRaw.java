package br.com.Isabela01vSilva.schedulo.service;

import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.sns.SnsClient;
import software.amazon.awssdk.services.sns.model.*;

import java.util.Map;

public class SnsPublisherRaw {

    private final SnsClient snsClient;

    public SnsPublisherRaw() {
        this.snsClient = SnsClient.builder()
                .region(Region.US_EAST_1)
                .build();
    }

    public void publicar(String arnTopico, String mensagem, String appName) {
        PublishRequest request = PublishRequest.builder()
                .topicArn(arnTopico)
                .message(mensagem)
                .messageAttributes(
                        Map
                        .of("appName", MessageAttributeValue
                                .builder()
                                .dataType("String")
                                .stringValue(appName)
                                .build())
                )
                .build();

        snsClient.publish(request);
    }
}
