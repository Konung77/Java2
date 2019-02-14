package lesson1;

import java.util.ArrayList;
import java.util.List;

import lesson1.particiant.*;

/**
 * @author ilnaz-92@yandex.ru
 * Created on 11/02/2019
 */
public class TournamentLauncher
{
  public static void main(String[] args)
  {
    List<Animal> members = new ArrayList<>();

    Animal member1 = new Cat(5, "BARSIK", Cat.TYPE_BRITYSH);
    Animal member2 = new Cat(10, "KISYA", Cat.TYPE_PERSIA);
    Animal member3 = new Dog(3, "MUHTAR", Dog.TYPE_BULDOG);
    Animal member4 = new Human(23, "Ivan ivanov", Gender.MALE);

    Course c = new Course(); // Создаем полосу препятствий
    Team team = new Team("Команда", member1, member2, member3, member4); // Создаем команду
    c.doIt(team); // Просим команду пройти полосу
    team.showResults(); // Показываем результаты
  }
}
