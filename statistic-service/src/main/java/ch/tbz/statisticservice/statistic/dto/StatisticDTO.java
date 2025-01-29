package ch.tbz.statisticservice.statistic.dto;

import ch.tbz.statisticservice.statistic.Status;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticDTO {

    private Integer categoryId;

    private Status status;

    private Integer count;
}
