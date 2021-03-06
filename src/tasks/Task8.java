package tasks;

import common.Person;
import common.Task;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/*
А теперь о горьком
Всем придется читать код
А некоторым придется читать код, написанный мною
Сочувствую им
Спасите будущих жертв, и исправьте здесь все, что вам не по душе!
P.S. функции тут разные и рабочие (наверное), но вот их понятность и эффективность страдает (аж пришлось писать комменты)
P.P.S Здесь ваши правки желательно прокомментировать (можно на гитхабе в пулл реквесте)
 */
public class Task8 implements Task {

  //Не хотим выдывать апи нашу фальшивую персону, поэтому конвертим начиная со второй
  public List<String> getNames(List<Person> persons) {
    if (persons.isEmpty()) {
      return Collections.emptyList();
    }
    // Здесь я не удаляю первую персону из листа, скипая ее в стриме.
    // Эта логика вообще нужна? Не очень очевидно из названия.
    return persons.stream()
            .skip(1)
            .map(Person::getFirstName)
            .collect(Collectors.toList());
  }

  //ну и различные имена тоже хочется
  public Set<String> getDifferentNames(List<Person> persons) {
      // Нет смысла применять distinct
      // т.к. у нас и так на выходе Set с уникальными элементами.
      // Также, тут можно обойтись совсем без стрима. (Спасибо IDEA :D)
    return new HashSet<String>(getNames(persons));
  }

  //Для фронтов выдадим полное имя, а то сами не могут
  public String convertPersonToString(Person person) {
    // second name повторяется и тут что-то не так с порядком -
    // second first second, правильней было бы first middle second
    // ну и можно переписать на стримы
    return Stream.of(person.getFirstName(), person.getMiddleName(), person.getSecondName())
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" "));
  }

  // словарь id персоны -> ее имя
  public Map<Integer, String> getPersonNames(Collection<Person> persons) {
    // Stream api как по мне тут лучше,
    return persons.stream()
            .collect(Collectors.toMap(Person::getId, 
                                      this::convertPersonToString
                                      (old, new) -> old));
  }

  // есть ли совпадающие в двух коллекциях персоны?
  public boolean hasSamePersons(Collection<Person> persons1, Collection<Person> persons2) {
    // Лучше применить готовый оптимизированный метод disjoint.
    // - Мы в том варианте не выходили из цикла, как нашли совпадение.
    // - disjoint использует .contains(e) вместо второго цикла,
    //       если одна из коллекций это Set, мы быстрее найдем совпадение.
    return !Collections.disjoint(persons1, persons2);
  }

  //...
  public long countEven(Stream<Integer> numbers){
    // нам переменная count вне этого метода нужна?
    return numbers.filter(num -> num % 2 == 0).count();
  }

  @Override
  public boolean check() {
    System.out.println("Слабо дойти до сюда и исправить Fail этой таски?");
    boolean codeSmellsGood = Boolean.parseBoolean("I don't think so");
    boolean reviewerDrunk = ! !!! ! !! ! true;
    return codeSmellsGood || reviewerDrunk;
  }
}
