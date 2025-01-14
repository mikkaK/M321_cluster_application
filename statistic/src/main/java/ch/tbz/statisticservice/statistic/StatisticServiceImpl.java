package ch.tbz.statisticservice.statistic;

import ch.tbz.statisticservice.core.generic.AbstractEntity;
import ch.tbz.statisticservice.core.generic.AbstractRepository;
import ch.tbz.statisticservice.core.generic.AbstractServiceImpl;
import ch.tbz.statisticservice.statistic.dto.StatisticDTO;
import ch.tbz.statisticservice.statistic.dto.StatisticResponseDTO;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class StatisticServiceImpl extends AbstractServiceImpl<AbstractEntity> implements StatisticService {
    protected StatisticServiceImpl(AbstractRepository<AbstractEntity> repository) {
        super(repository);
    }

    @Override
    public StatisticResponseDTO findByUserAndCategory(Long userId, Long categoryId) {
        StatisticResponseDTO statistics = null;

        if (categoryId != null){
            List<Task> tasks = repository.findByUserIdAndCategoryId(userId, categoryId);
            List<Task> unfinished = tasks.stream().filter(task -> task.status == Status.UNFINISHED);
            List<Task> inProcess = tasks.stream().filter(task -> task.status == Status.IN_PROCESS);
            List<Task> finished = tasks.stream().filter(task -> task.status == Status.FINISHED);

            StatisticDTO unfinishedResponse = new StatisticDTO();
            unfinishedResponse.setStatus(Status.UNFINISHED);
            unfinishedResponse.setCount(unfinished.size());
            unfinishedResponse.setCategoryId(categoryId);

            StatisticDTO inProcessResponse = new StatisticDTO();
            inProcessResponse.setStatus(Status.IN_PROCESS);
            inProcessResponse.setCount(inProcess.size());
            inProcessResponse.setCategoryId(categoryId);

            StatisticDTO finishedResponse = new StatisticDTO();
            finishedResponse.setStatus(Status.FINISHED);
            finishedResponse.setCount(finished.size());
            finishedResponse.setCategoryId(categoryId);

            statistics.setStatisticDTOs(List.of(unfinishedResponse, inProcessResponse, finishedResponse));

        } else {
            List<Task> tasks = repository.findByUserId(userId);
            List<Task> unfinished = tasks.stream().filter(task -> task.status == Status.UNFINISHED);
            List<Task> inProcess = tasks.stream().filter(task -> task.status == Status.IN_PROCESS);
            List<Task> finished = tasks.stream().filter(task -> task.status == Status.FINISHED);

            StatisticDTO unfinishedResponse = new StatisticDTO();
            unfinishedResponse.setStatus(Status.UNFINISHED);
            unfinishedResponse.setCount(unfinished.size());

            StatisticDTO inProcessResponse = new StatisticDTO();
            inProcessResponse.setStatus(Status.IN_PROCESS);
            inProcessResponse.setCount(inProcess.size());

            StatisticDTO finishedResponse = new StatisticDTO();
            finishedResponse.setStatus(Status.FINISHED);
            finishedResponse.setCount(finished.size());

            statistics.setStatisticDTOs(List.of(unfinishedResponse, inProcessResponse, finishedResponse));
        }

        return statistics;
    }
}
