package ch.tbz.statisticservice.statistic;

import ch.tbz.statisticservice.statistic.dto.StatisticResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/statistic")
public class StatisticController {

    private final StatisticService statisticService;

    public StatisticController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }


    @GetMapping("/user/{id}/category/{id}")
    public ResponseEntity<StatisticResponseDTO> getStatistics(@PathVariable Long userId, @PathVariable Long categoryId) {
        log.trace("Getting statistics for userId: {} and categoryId: {}", userId, categoryId);
        StatisticResponseDTO responseDTO = statisticService.findByUserAndCategory(userId, categoryId);
        return new ResponseEntity<>(responseDTO, HttpStatus.OK);
    }
}
