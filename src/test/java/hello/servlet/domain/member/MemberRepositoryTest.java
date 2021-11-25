package hello.servlet.domain.member;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.*;

class MemberRepositoryTest {

    MemberRepository memberRepository= MemberRepository.getInstance();

    @AfterEach // 테스트는 순서가 보장되지 않으므로 한개의 테스트를 실행할때마다,
    // 값을 리셋하여 테스트 결과가 다르게 나오지 않도록 사전에 방지한다.
    void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void save(){
        //given
        Member member = new Member("hello",20);
        //when
        Member savedMember = memberRepository.save(member);
        //then
        Member findMember = memberRepository.findByid(savedMember.getId());
        assertThat(findMember).isEqualTo(savedMember);
        // assert~메소드를 통해서 테스트 성공 여부를 확인 가능
    }
    @Test
    void findAll(){
        //given
        Member member1 = new Member("member1",20);
        Member member2 = new Member("member2",30);

        memberRepository.save(member1);
        memberRepository.save(member2);
        //when
        List<Member> result = memberRepository.findAll();
        //then
           assertThat(result.size()).isEqualTo(2);
           assertThat(result).contains(member1,member2);

    }

}