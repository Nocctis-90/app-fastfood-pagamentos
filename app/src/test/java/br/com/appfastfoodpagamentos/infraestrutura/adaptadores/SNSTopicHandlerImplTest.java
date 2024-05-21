package br.com.appfastfoodpagamentos.infraestrutura.adaptadores;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.amazonaws.services.sns.AmazonSNS;
import com.amazonaws.services.sns.model.PublishRequest;
import com.amazonaws.services.sns.model.PublishResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class SNSTopicHandlerImplTest {

    @Mock
    private AmazonSNS snsClient;

    private SNSTopicHandlerImpl snsTopicHandler;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        snsTopicHandler = new SNSTopicHandlerImpl(snsClient);
    }

    @Test
    void publish_Success() {
        // Arrange
        String message = "Test message";
        String topicArn = "arn:aws:sns:us-east-1:123456789012:MyTopic";

        PublishResult publishResult = new PublishResult().withMessageId("messageId");
        when(snsClient.publish(any(PublishRequest.class))).thenReturn(publishResult);

        // Act
        snsTopicHandler.publish(message, topicArn);

        // Assert
        verify(snsClient, times(1)).publish(any(PublishRequest.class));
        // Verifique se a mensagem de sucesso é impressa
        // Isso só funciona se a classe realmente imprime a mensagem em caso de sucesso
    }

    @Test
    void publish_Failure() {
        // Arrange
        String message = "Test message";
        String topicArn = "arn:aws:sns:us-east-1:123456789012:MyTopic";

        when(snsClient.publish(any(PublishRequest.class))).thenThrow(new RuntimeException("Test exception"));

        // Act
        snsTopicHandler.publish(message, topicArn);

        // Assert
        verify(snsClient, times(1)).publish(any(PublishRequest.class));
        // Verifique se a mensagem de erro é impressa
        // Isso só funciona se a classe realmente imprime a mensagem em caso de erro
    }
}
