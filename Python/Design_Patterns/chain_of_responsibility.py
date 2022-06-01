"""
Avoid coupling the sender of a request to its receiver by giving
more than one object a chance to handle the request. Chain the receiving
objects and pass the request along the chain until an object handles it.
"""

import abc


class Handler(metaclass=abc.ABCMeta):
    """
    Define an interface for handling requests.
    Implement the successor link.
    """

    def __init__(self, successor=None):
        self._successor = successor

    @abc.abstractmethod
    def handle_request(self):
        pass


class ConcreteHandler1(Handler):
    """
    Handle request, otherwise forward it to the successor.
    """

    def handle_request(self):
        if True:  # if can_handle:
            pass
        elif self._successor is not None:
            self._successor.handle_request()


class ConcreteHandler2(Handler):
    """
    Handle request, otherwise forward it to the successor.
    """

    def handle_request(self):
        if False:  # if can_handle:
            pass
        elif self._successor is not None:
            self._successor.handle_request()


def main():
    concrete_handler_1 = ConcreteHandler1()
    concrete_handler_2 = ConcreteHandler2(concrete_handler_1)
    concrete_handler_2.handle_request()


if __name__ == "__main__":
    main()
