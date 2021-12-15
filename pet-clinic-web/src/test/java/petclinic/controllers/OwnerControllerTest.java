package petclinic.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;
import petclinic.model.Owner;
import petclinic.services.OwnerService;

import java.util.HashSet;
import java.util.Set;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class OwnerControllerTest {
    @InjectMocks
    OwnerController ownerController;
    @Mock
    OwnerService ownerService;
    @Mock
    Model model;
    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        //MockitoAnnotations.openMocks(this); //with @ExtendWith(MockitoExtension.class) openMocks in not needed
        mockMvc = MockMvcBuilders.standaloneSetup(ownerController).build();
    }

    @Test
    void indexFun() throws Exception {
        Set<Owner> owners = new HashSet<>();
        owners.add(Owner.builder().id(1L).build());
        owners.add(Owner.builder().id(2L).build());

        when(ownerService.findAll()).thenReturn(owners);

        mockMvc.perform(get("/owners/"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("owners/index"))
                .andExpect(model().attribute("owners", hasSize(2)));
    }

    @Test
    void findOwner() throws Exception {
        mockMvc.perform(get("/owners/find"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(view().name("notimplemented"))
                .andExpect(model().attributeDoesNotExist("owners"));
    }
}