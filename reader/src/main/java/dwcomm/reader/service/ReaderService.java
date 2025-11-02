package dwcomm.reader.service;

import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReaderService {


    private final List<Long> lList = new ArrayList<>();

    @KafkaListener(
            // Определяет группу консюмера
            id = "group5",
            // Определяет топик откуда читаем
            topics = "${kafka.topics.long-topic}",
            // ВАЖНО: определяет фабрику, которую мы используем. Иначе используется фабрика по умолчанию и многопоточность не работает
            containerFactory = "kafkaListenerContainerFactory")
    public void handle(@Payload Long id) {
        lList.add(id);
    }

    @KafkaListener(
            // Определяет группу консюмера
            id = "group6",
            // Определяет топик откуда читаем
            topics = "${kafka.topics.list-long-topic}",
            // ВАЖНО: определяет фабрику, которую мы используем. Иначе используется фабрика по умолчанию и многопоточность не работает
            containerFactory = "kafkaListenerContainerFactory")
    public void handleListLong(@Payload List<Long> list) {
        lList.addAll(list);
    }

    public Integer getMessagesCount() {
        return lList.size();
    }
}
