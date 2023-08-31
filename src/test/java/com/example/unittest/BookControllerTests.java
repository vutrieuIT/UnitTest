package com.example.unittest;

import com.example.unittest.controller.BookController;
import com.example.unittest.dto.Book;
import com.example.unittest.service.BookServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.charset.StandardCharsets;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest(classes = UnitTestApplicationTests.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class BookControllerTests {

    @Mock
    private BookServiceImpl bookService;

    @InjectMocks
    private BookController bookController;

    @Autowired
    private MockMvc mockMvc; // mock request api

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("get book test")
    public void getBookTest() throws Exception {

        // mock request
        String content = mockMvc.perform(
                        MockMvcRequestBuilders.get("/book/1")
                )
                .andExpect(status().is(200))
                .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);


    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("save book test")
    class saveBookTest {

        @Test
        public void success() throws Exception {
            // mock data
            Book mockBook = new Book();
            mockBook.setId(1L);
            mockBook.setTitle("title");
            mockBook.setAuthor("author");

            // mock service
            when(bookService.saveBook(mockBook)).thenReturn(mockBook);

            ObjectMapper mapper = new ObjectMapper();
            // mock request
            String content = mockMvc.perform(
                            MockMvcRequestBuilders.post("/book/save")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(mapper.writeValueAsString(mockBook))
                                    .characterEncoding("utf-8")
                    )
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
            assertThat(content).isEqualTo(mapper.writeValueAsString(mockBook));
        }

        @Test
        public void fail() throws Exception {
            // mock data
            Book mockBook = new Book();

            // mock service
            when(bookService.saveBook(mockBook)).thenReturn(mockBook);

            ObjectMapper mapper = new ObjectMapper();
            // mock request
            String content = mockMvc.perform(
                            MockMvcRequestBuilders.post("/book/save")
                                    .contentType(MediaType.APPLICATION_JSON)
                                    .content(mapper.writeValueAsString(mockBook))
                                    .characterEncoding(StandardCharsets.UTF_8)
                    )
                    .andExpect(status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);

            String expectedMessage = "{\"message\":\"Request Body : book null\"}";
            assertThat(content).isEqualTo(expectedMessage);
        }

    }

    @TestInstance(TestInstance.Lifecycle.PER_CLASS)
    @Nested
    @DisplayName("delete book test")
    class DeleteBookTest {
        @BeforeAll
        public void mockData() {
            // mock service
            when(bookService.deleleBook(1L)).thenReturn(true);
            when(bookService.deleleBook(2L)).thenReturn(false);
        }

        @Test
        @DisplayName("success")
        public void successTest() throws Exception {
            String content = mockMvc
                    .perform(
                            MockMvcRequestBuilders.delete("/book/1")
                    )
                    .andExpect(status().isOk())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
            String expectedContent = "{\"message\":\"done\"}";
            assertThat(content).isEqualTo(expectedContent);
        }

        @Test
        @DisplayName("fail")
        public void failTest() throws Exception {
            String content = mockMvc
                    .perform(
                            MockMvcRequestBuilders.delete("/book/2")
                    )
                    .andExpect(status().isBadRequest())
                    .andReturn().getResponse().getContentAsString(StandardCharsets.UTF_8);
            String expectedContent = "{\"message\":\"fail\"}";
            assertThat(content).isEqualTo(expectedContent);
        }
    }
}
