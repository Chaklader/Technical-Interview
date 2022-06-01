"""
Attach additional responsibilities to an object dynamically. Decorators
provide a flexible alternative to subclassing for extending
functionality.
"""

import abc


class Component(metaclass=abc.ABCMeta):
    """
    Define the interface for objects that can have responsibilities
    added to them dynamically.
    """

    @abc.abstractmethod
    def operation(self):
        pass


class Decorator(Component, metaclass=abc.ABCMeta):
    """
    Maintain a reference to a Component object and define an interface
    that conforms to Component's interface.
    """

    def __init__(self, component):
        self._component = component

    @abc.abstractmethod
    def operation(self):
        pass


class ConcreteDecoratorA(Decorator):
    """
    Add responsibilities to the component.
    """

    def operation(self):
        # ...
        self._component.operation()
        # ...


class ConcreteDecoratorB(Decorator):
    """
    Add responsibilities to the component.
    """

    def operation(self):
        # ...
        self._component.operation()
        # ...


class ConcreteComponent(Component):
    """
    Define an object to which additional responsibilities can be
    attached.
    """

    def operation(self):
        pass


def main():
    concrete_component = ConcreteComponent()
    concrete_decorator_a = ConcreteDecoratorA(concrete_component)
    concrete_decorator_b = ConcreteDecoratorB(concrete_decorator_a)
    concrete_decorator_b.operation()


if __name__ == "__main__":
    main()
