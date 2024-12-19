class Beast {
  void eat() {
    System.out.println("Животные питаются:");
    }
  }
  class herbivorous extends Beast {
    void eat() {
      System.out.println("Травоядные едят растения");
    }
  }
  class omnivorous extends Beast {
    void eat() {
      System.out.println("Всеядные едят растения и мясо");
    }
  }
  class carnivorous extends Beast {
    void eat() {
      System.out.println("Хищники едят только мясо");
    }
  }
  class main {
    public static void main(String args[]) {
      Beast X = new Beast();
      Beast herb = new herbivorous();
      Beast omni = new omnivorous();
      Beast carn = new carnivorous();
      X.eat();
      herb.eat();
      omni.eat();
      carn.eat();

    }
  }
