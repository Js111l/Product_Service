package com.ecom.productservice.controller;

import com.ecom.productservice.service.ClassService;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/enums")
public class EnumController {
    private final ClassService classService = new ClassService();

    @GetMapping("/{className}/values")
    @CrossOrigin
    public List<String> getByType(@PathVariable String className) throws IOException, InvocationTargetException, NoSuchMethodException, IllegalAccessException {
        return Arrays.stream(this.classService.getEnumValuesByType(className)).map(Enum::name).toList();
    }

}
