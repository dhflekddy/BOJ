import java.lang.reflect.Method;
import java.util.Objects;

/**
 * 플러그 가능 어댑터: 객체 어댑터
 * 자바의 reflection library 이용
 * 
 * 클라이언트가 adaptee(A)를 자기가 원하는 B타입으로 사용할 수 있게 해주는 것입니다. 여기서 이미 제공되어 있는것(A)의 메서드 이름과 매개변수가 필요한 것(B)의 그것과 다를 수 있다. 이 다른것을 맞추어 주기 위해  어댑터 패턴을 쓰는 것임. 

아… 이제 이해가 간다. 이미 있는 것을 자신이 원하는 방식대로 쓸 수 있게 해주기 위해서 자신이 원하는 방식을 인터페이스로 여기고 구현하는 것(implements)이고 이미 있는 것을 내가 원하는 대로 쓸수 있어야 하기 때문에 이미 있는 것을 has-a관계로 두고 원하는 방식을 구현할 때 그 기존에 있는 것의 함수로 구현하는 것이다!!!(나누어준 예제보면 바로 이해함)

 */
public class Adapter implements Target{//이미있는 것(Cat, Dog, Frog)을 자신이 원하는 것(Target)으로 바꾸어 쓸수 있게 해주는 어댑터(Adapter)
	Object adaptee;
	String cryfunc;
	public Adapter(Object adaptee, String cryfunc) {
		this.adaptee = Objects.requireNonNull(adaptee);
		this.cryfunc = Objects.requireNonNull(cryfunc);
	}
	@Override
	public void foo() {
		try {
			Class<?> adapteeClass = adaptee.getClass();
			Method cry = adapteeClass.getMethod(cryfunc);
			cry.invoke(adaptee);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
