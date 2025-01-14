package ch.tbz.statisticservice.statistic.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StatisticResponseDTO {
    List<StatisticDTO> statisticDTOs;
}
