package com.moment.the.uncomfortable.service;

import com.moment.the.exceptionAdvice.exception.GoodsNotCancelException;
import com.moment.the.exceptionAdvice.exception.NoPostException;
import com.moment.the.uncomfortable.UncomfortableEntity;
import com.moment.the.uncomfortable.dto.UncomfortableSetDto;
import com.moment.the.uncomfortable.dto.UncomfortableGetDto;
import com.moment.the.uncomfortable.repository.UncomfortableRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class UncomfortableService {
    private final UncomfortableRepository uncomfortableRepository;

    /**
     * 학교의 불편함을 작성합니다.
     * @param uncomfortableSetDto
     * @return UncomfortableEntity
     */
    @Transactional
    public UncomfortableEntity addUncomfortable(UncomfortableSetDto uncomfortableSetDto){
        return uncomfortableRepository.save(uncomfortableSetDto.toEntity());
    }

    /**
     * 많은 학생들이 공감한 글 상위 30개를 선별하여 가져옵니다.
     * @return List<UncomfortableGetDto>
     */
    public List<UncomfortableGetDto> getTop30() {
        return uncomfortableRepository.uncomfortableViewTopBy(PageRequest.of(0,30));
    }

    /**
     * 학교의 불편함 전체를 가져옵니다.
     * @return List<UncomfortableGetDto>
     */
    public List<UncomfortableGetDto> getAllUncomfortable(){
        return uncomfortableRepository.uncomfortableViewAll();
    }

    /**
     * 해당 불편함의 좋아요를 증가시킵니다.
     * @param boardIdx
     */
    @Transactional
    public void increaseLike(Long boardIdx){
        UncomfortableEntity uncomfortableEntity = uncomfortableRepository.findByBoardIdx(boardIdx).orElseThrow(NoPostException::new);
        uncomfortableEntity.updateGoods(uncomfortableEntity.getGoods()+1);
    }

    /**
     * 해당 불편함의 좋아요를 감소시킵니다.
     * @param boardIdx
     */
    @Transactional
    public void decreaseLike(Long boardIdx) {
        UncomfortableEntity uncomfortableEntity = uncomfortableRepository.findByBoardIdx(boardIdx).orElseThrow(NoPostException::new);
        int goodsResult = uncomfortableEntity.getGoods() - 1;

        if(goodsResult > -1) {//좋야요가 양수일때
            uncomfortableEntity.updateGoods(goodsResult);
        }else{
            throw new GoodsNotCancelException();
        }
    }

    /**
     * 해당 불편함을 삭제합니다.
     * @param boardIdx
     */
    @Transactional
    public void deleteUncomfortable(long boardIdx){
        uncomfortableRepository.deleteById(boardIdx);
    }

    /**
     * 불편함의 개수를 세어 가져옵니다.
     * @return Long
     */
    public Long getNumberOfUncomfortable(){
        return uncomfortableRepository.amountUncomfortable();
    }

    /**
     * 프로젝트 D-day를 세어 가져옵니다.
     * @return int
     */
    public int getDateSinceProjectStart(){
        return calculateAfterDate();
    }

    /**
     * D-day를 계산하는 메서드.
     * @return int
     */
    public static int calculateAfterDate(){
         //  today: 오늘 날짜
         //  theMomentStart: the-moment 시작 날짜
        LocalDate today = LocalDate.now();
        LocalDate theMomentStart = LocalDate.of(2021, 6, 7);

        // the_moment 프로젝트를 시작한 날짜 by 오늘의 날짜
        int period = (int) theMomentStart.until(today, ChronoUnit.DAYS);

        return period;
    }
}
