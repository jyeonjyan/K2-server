package com.moment.the.uncomfortable.controller;

import com.moment.the.response.ResponseService;
import com.moment.the.response.result.CommonResult;
import com.moment.the.response.result.ListResult;
import com.moment.the.response.result.SingleResult;
import com.moment.the.uncomfortable.dto.UncomfortableSetDto;
import com.moment.the.uncomfortable.dto.UncomfortableGetDto;
import com.moment.the.uncomfortable.service.TableService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/v1")
public class TableController {
    //Dependency Injection
    private final TableService tableService;
    private final ResponseService responseService;

    // localhost:8080/v1/uncomfortable
    @PostMapping("/uncomfortable")
    public CommonResult write(@Valid @RequestBody UncomfortableSetDto uncomfortableSetDto){
        tableService.write(uncomfortableSetDto);
        return responseService.getSuccessResult();
    }

    // localhost:8080/v1/uncomfortable/top30
    @GetMapping("/uncomfortable/top30")
    public ListResult<UncomfortableGetDto> top10(){
        return responseService.getListResult(tableService.top30View());
    }

    // localhost:8080/v1/uncomfortable
    @GetMapping("/uncomfortable")
    public ListResult<UncomfortableGetDto> viewAll(){
        return responseService.getListResult(tableService.viewAll());
    }

    // localhost:8080/v1/uncomfortable/{boardIdx}
    @PutMapping("/uncomfortable/{boardIdx}")
    public CommonResult goods(@PathVariable Long boardIdx){
        tableService.goods(boardIdx);
        return responseService.getSuccessResult();
    }

    // localhost:8080/v1/uncomfortable/cancel/{boardIdx}
    @PutMapping("/uncomfortable/cancel/{boardIdx}")
    public CommonResult cancelGood(@PathVariable Long boardIdx){
        tableService.cancelGood(boardIdx);
        return responseService.getSuccessResult();
    }

    // localhost:8080/v1/uncomfortable/amount
    @GetMapping("/uncomfortable/amount")
    public SingleResult<Long> amountUncomfortable(){
        return responseService.getSingleResult(tableService.amountUncomfortableView());
    }

    // localhost:8080/v1/uncomfortable/dateSinceProjectStart
    @GetMapping("/uncomfortable/dateSinceProjectStart")
    public SingleResult<Integer> getDateSinceProjectStart(){
        return responseService.getSingleResult(tableService.dateSinceProjectStart());
    }

    // localhost:8080/v1/uncomfortable/{boardIdx}
    @DeleteMapping("/uncomfortable/{boardIdx}")
    public CommonResult deleteThisBoard(@PathVariable Long boardIdx){
        tableService.delete(boardIdx);
        return responseService.getSuccessResult();
    }
}
