package org.example.functional;

import java.util.function.Consumer;

public class Resource {
    private Resource() {
        System.out.println("Open DB connection...");
    }
    public Resource op1() {
        System.out.println("op1");
        return this;
    }
    public Resource op2() {
        System.out.println("op2");
        return this;
    }
    private void close() {
        System.out.println("Closing connection...");
    }
    public static void use(Consumer<Resource> block) {
        Resource resource = new Resource();
      try {
          block.accept(resource);
      } finally {
          resource.close();
      }

    }

    public static void main(String[] args) {
        Resource.use(resource ->
                resource.op1()
                        .op2()
        );
    }
}
