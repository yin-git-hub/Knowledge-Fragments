package org.example;

public class UserService {//gnome
    private String componentName;

    // Setter方法，用于注入userRepository Cache of singleton objects: bean name to bean instance.
    public void setComponentName(String componentName) {
        this.componentName = componentName;
        /**
         * Retrieve the flag for the given property key.
         * Params:
         * key – the property key
         * Returns:
         * true if the property is set to "true", false otherwise
         */
    }

    public String getComponentName() {
        return componentName;
    }

    @Override
    public String toString() {
        return "UserService{" +
                "componentName='" + componentName + '\'' +
                '}';
    }
}
