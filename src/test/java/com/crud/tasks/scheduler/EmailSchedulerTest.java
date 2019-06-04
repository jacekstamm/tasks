package com.crud.tasks.scheduler;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import com.icegreen.greenmail.util.GreenMail;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import javax.annotation.Resource;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class EmailSchedulerTest {


    @Autowired
    private TaskRepository taskRepository;
    @Autowired
    private EmailScheduler emailScheduler;

    @Resource
    private JavaMailSenderImpl javaMailSender;
    private GreenMail greenMail;


    @Before
    public void testSmtpInit() {

    }

    @Test
    public void testMail() {
        //Given
        Task cleaning = new Task("Sprzątanie", "Sprzątanie pokoju");
        taskRepository.save(cleaning);

        //When





        emailScheduler.sendInformationEmail();
        //Then



        //CleanUp





        taskRepository.delete(cleaning.getId());


    }
}