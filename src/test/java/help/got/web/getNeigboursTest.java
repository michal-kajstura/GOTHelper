package help.got.web;

import help.got.data.LineRepository;
import help.got.model.Line;
import help.got.model.Point;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanTripController.class)
@ActiveProfiles("sandbox")
public class getNeigboursTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanTripController planTrip;

    private static final Long TEST_ID = 1L;

    @Test
    public void getNeighboursTest() throws Exception{
        var point = new Point(TEST_ID, "test", 1.1, 1.2, 1.1, 1.2);
        var line = new Line();
        line.setLineId(TEST_ID);
        var result = Arrays.asList(line);
        var lineRepo = Mockito.mock(LineRepository.class);
        Mockito.when(lineRepo.getNeighbourPaths(point.getIdP())).thenReturn(result);
        planTrip = new PlanTripController(
               null, lineRepo, null, null);

        mockMvc.perform(get("/plan_trip/points/1"))
                .andExpect(status().isOk());
    }

}
