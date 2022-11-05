package com.patiun.informationhandling.component;

import java.util.ArrayList;
import java.util.List;

public class Composite implements Component {

    private final List<Component> components = new ArrayList<>();

    public Composite() {
    }

    public Composite(List<? extends Component> components) {
        this.components.addAll(components);
    }

    public void add(Component component) {
        components.add(component);
    }

    public List<Component> getChildren() {
        return new ArrayList<>(components);
    }

    public int getChildrenNumber() {
        return components.size();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Composite composite = (Composite) o;
        return components.equals(composite.components);
    }

    @Override
    public int hashCode() {
        return components.hashCode();
    }

    @Override
    public String toString() {
        return "Composite{" +
                "components=" + components +
                '}';
    }
}
