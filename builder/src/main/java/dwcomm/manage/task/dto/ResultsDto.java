package dwcomm.manage.task.dto;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

// Подумать над применением маппера structs
@RequiredArgsConstructor
@Getter
public class ResultsDto {

    private final Integer countOfLongs;
    private final Integer countOfListOfLongs;

    private final List<Long> resultLongs;
    private final List<List<Long>> resultListOfLongs;
}
