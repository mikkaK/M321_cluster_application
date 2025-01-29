package ch.tbz.statisticservice.statistic;

import ch.tbz.statisticservice.core.generic.AbstractEntity;
import ch.tbz.statisticservice.core.generic.AbstractRepository;
import ch.tbz.statisticservice.core.generic.AbstractServiceImpl;
import ch.tbz.statisticservice.task.Task;
import ch.tbz.statisticservice.statistic.dto.StatisticDTO;
import ch.tbz.statisticservice.statistic.dto.StatisticResponseDTO;
import ch.tbz.statisticservice.task.TaskRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j2
public class StatisticServiceImpl implements StatisticService {

    private final TaskRepository taskRepository;

    protected StatisticServiceImpl(AbstractRepository<Task> repository, TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public StatisticResponseDTO findByUserAndCategory(String user, String category) {
        StatisticResponseDTO statistics = null;

        if (category != null){
            List<Task> tasks = taskRepository.findByUserAndCategory(user, category);
            List<Task> unfinished = tasks.stream().filter(task -> !task.isDone()).toList();
            List<Task> finished = tasks.stream().filter(Task::isDone).toList();

            StatisticDTO unfinishedResponse = new StatisticDTO();
            unfinishedResponse.setStatus(Status.UNFINISHED);
            unfinishedResponse.setCount(unfinished.size());

            StatisticDTO finishedResponse = new StatisticDTO();
            finishedResponse.setStatus(Status.FINISHED);
            finishedResponse.setCount(finished.size());

            statistics.setStatisticDTOs(List.of(unfinishedResponse, finishedResponse));

        } else {
            List<Task> tasks = this.taskRepository.findByUser(user);
            List<Task> unfinished = tasks.stream().filter(task -> !task.isDone()).toList();
            List<Task> finished = tasks.stream().filter(Task::isDone).toList();

            StatisticDTO unfinishedResponse = new StatisticDTO();
            unfinishedResponse.setStatus(Status.UNFINISHED);
            unfinishedResponse.setCount(unfinished.size());

            StatisticDTO finishedResponse = new StatisticDTO();
            finishedResponse.setStatus(Status.FINISHED);
            finishedResponse.setCount(finished.size());

            statistics.setStatisticDTOs(List.of(unfinishedResponse, finishedResponse));
        }

        return statistics;
    }
}
