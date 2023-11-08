package analix.DHIT;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//http://localhost:8080/manager/report-search

//↓Spring Bootはここから始まる
//componentscanしてる↓
@SpringBootApplication
public class DHitApplication {

	//↓これは自分の所属してるクラスDHitApplicationを呼び出している
	public static void main(String[] args) {
		SpringApplication.run(DHitApplication.class, args);
	}

}
