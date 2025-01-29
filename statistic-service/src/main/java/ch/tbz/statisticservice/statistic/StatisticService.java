package ch.tbz.statisticservice.statistic;

import ch.tbz.statisticservice.core.generic.AbstractEntity;
import ch.tbz.statisticservice.core.generic.AbstractService;
import ch.tbz.statisticservice.statistic.dto.StatisticResponseDTO;

public interface StatisticService {
    StatisticResponseDTO findByUserAndCategory(String user, String category);
}
