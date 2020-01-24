package help.got.web;

import help.got.data.PointRepository;
import help.got.model.Point;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(PlanTripController.class)
@ActiveProfiles("sandbox")
public class getNeigboursTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PlanTripController planTrip;

    @Test
    public void getNeighboursTest() throws Exception{
        var pointRepo = Mockito.mock(PointRepository.class);
        var point = new Point(1L, "test", 1.1, 1.2, 1.1, 1.2);
        planTrip = new PlanTripController(
                pointRepo, null, null, null);


        Mockito.when(pointRepo.findPointByIdP(1L)).thenReturn(point);
        mockMvc.perform(get("/plan_trip/points/1"))
                .andExpect(status().isOk());
    }

}
