package com.example.mojal2ndproject2.exchangepost;

import com.example.mojal2ndproject2.exchangepost.model.ExchangePost;
import com.example.mojal2ndproject2.member.model.Member;
import com.example.mojal2ndproject2.sharePost.model.SharePost;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ExchangePostRepository extends JpaRepository<ExchangePost, Long> {
    List<ExchangePost> findAllByMember(Member member);
    @Query("SELECT ep FROM ExchangePost ep JOIN FETCH ep.member JOIN FETCH ep.giveCategory JOIN FETCH ep.takeCategory WHERE ep.member= :member")
    List<ExchangePost> findAllByMemberWithMemberAndCategory(Member member);
    @Query("SELECT ep FROM ExchangePost ep JOIN FETCH ep.member JOIN FETCH ep.giveCategory JOIN FETCH ep.takeCategory WHERE ep.member= :member")
    Slice<ExchangePost> findAllByMemberWithMemberAndCategory(Pageable pageable);
    @Query("SELECT ep FROM ExchangePost ep JOIN FETCH ep.postMatchingMembers JOIN FETCH ep.giveCategory JOIN FETCH ep.takeCategory")
    List<ExchangePost> findAllByMemberWithMatchingMemberAndGiveCategoryAndTakeCategory(Member member);
    @Query("SELECT ep FROM ExchangePost ep JOIN FETCH ep.postMatchingMembers JOIN FETCH ep.giveCategory JOIN FETCH ep.takeCategory WHERE ep.member = :member")
    Slice<ExchangePost> findAllByMemberWithMatchingMemberAndGiveCategoryAndTakeCategory(Member member, Pageable pageable);//내가 작성한 교환글 전체 조회
    @Query("SELECT ep FROM ExchangePost ep JOIN FETCH ep.member JOIN FETCH ep.giveCategory JOIN FETCH ep.takeCategory") //교환글 전체 조회
//    List<ExchangePost> findAllPostWithMemberAndGiveCategoryAndTakeCategory();
    Slice<ExchangePost> findAllPostWithMemberAndGiveCategoryAndTakeCategory(Pageable pageable);
    @Query("SELECT ep FROM ExchangePost ep JOIN FETCH ep.member JOIN FETCH ep.giveCategory JOIN FETCH ep.takeCategory WHERE ep.idx = :postIdx")
    Optional<ExchangePost> findPostByIdxWithMemberAndGiveCategoryAAndTakeCategory(Long postIdx);
}