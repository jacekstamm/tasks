package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmailSchedulerTest {

    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmailScheduler emailScheduler;

    @Test
    public void testShouldNotRetrieveS() {
        //Given
        Task cleaning = new Task("Sprzątanie", "Sprzątanie pokoju");

        taskRepository.save(cleaning);
        //When
        emailScheduler.sendInformationEmail();

        //Then

        Assert.assertEquals(1, taskRepository.count());
        //CleanUp
        taskRepository.delete(cleaning.getId());
    }

    @Test
    public void testShouldRetrieveS() {
        //Given
        Task cleaning = new Task("Sprzątanie", "Sprzątanie pokoju");
        Task painting = new Task("Malowanie", "Łazienki");

        taskRepository.save(cleaning);
        taskRepository.save(painting);

        //When
        emailScheduler.sendInformationEmail();

        //Then
        Assert.assertEquals(2, taskRepository.count());

        //CleanUp
        taskRepository.delete(cleaning.getId());
        taskRepository.delete(painting.getId());
    }
}

