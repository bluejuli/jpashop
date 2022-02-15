package jpabook.jpashop;

import com.sun.org.apache.xpath.internal.operations.Or;
import jpabook.jpashop.domain.Order;
import jpabook.jpashop.domain.OrderItem;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
    public static void main(String[] args) {

        // 주의 : persistence.xml 에서 DB 파일 jpashop으로 반드시 변경할 것. 새로운 파일로 테스트할 예정
        // 비밀번호 추가할 것: pw: 1
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();
        EntityTransaction tx = em.getTransaction();
        tx.begin();

        try{
            // 단방향 관계만 설정하고 아래처럼 코딩해도 어플리케이션 개발에 전혀 문제 없다
            // 그럼에도 불구하고 양방향 관계를 설정하는 것은 추후 조회나 개발상의 편의 때문임
            // 또하나 이유: 실무에서는 JPQL도 많이 사용함. 이 떄 클래스 간에 참조의 편리성 때문에 양방향 설정이 필요 
//            Order order = new Order();
//            em.persist(order);
//
//            OrderItem orderItem = new OrderItem();
//            orderItem.setOrder(order);
//            em.persist(orderItem);

            // 양방향 연관관계 시 코딩 방법
            Order order = new Order();
            order.addOrderItem(new OrderItem());

            tx.commit();
        } catch(Exception e){
            tx.rollback();
        } finally {
            em.close(); // EntityManager가 내부적으로 DB 커넥션까지 물고 동작하기 때문에 여기서 크로징이 매우 중요
        }

        emf.close();
    }
}
