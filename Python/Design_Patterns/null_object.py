"""
Encapsulate the absence of an object by providing a substitutable
alternative that offers suitable default do nothing behavior.
"""

import abc


class AbstractObject(metaclass=abc.ABCMeta):
    """
    Declare the interface for Client's collaborator.
    Implement default behavior for the interface common to all classes,
    as appropriate.
    """

    @abc.abstractmethod
    def request(self):
        pass


class RealObject(AbstractObject):
    """
    Define a concrete subclass of AbstractObject whose instances provide
    useful behavior that Client expects.
    """

    def request(self):
        pass


class NullObject(AbstractObject):
    """
    Provide an interface identical to AbstractObject's so that a null
    object can be substituted for a real object.
    Implement its interface to do nothing. What exactly it means to do
    nothing depends on what sort of behavior Client is expecting.
    """

    def request(self):
        pass
