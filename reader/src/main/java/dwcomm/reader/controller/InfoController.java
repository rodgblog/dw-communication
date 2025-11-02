package dwcomm.reader.controller;


import dwcomm.reader.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class InfoController {

    private final ReaderService readerService;

    @GetMapping("/get-message-count")
    public Integer getMessagesCount() {
        return readerService.getMessagesCount();
    }
}
