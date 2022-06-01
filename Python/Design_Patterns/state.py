"""
Allow an object to alter its behavior when its internal state changes.
The object will appear to change its class.
"""

import abc


class Context:
    """
    Define the interface of interest to clients.
    Maintain an instance of a ConcreteState subclass that defines the
    current state.
    """

    def __init__(self, state):
        self._state = state

    def request(self):
        self._state.handle()


class State(metaclass=abc.ABCMeta):
    """
    Define an interface for encapsulating the behavior associated with a
    particular state of the Context.
    """

    @abc.abstractmethod
    def handle(self):
        pass


class ConcreteStateA(State):
    """
    Implement a behavior associated with a state of the Context.
    """

    def handle(self):
        pass


class ConcreteStateB(State):
    """
    Implement a behavior associated with a state of the Context.
    """

    def handle(self):
        pass


def main():
    concrete_state_a = ConcreteStateA()
    context = Context(concrete_state_a)
    context.request()


if __name__ == "__main__":
    main()
