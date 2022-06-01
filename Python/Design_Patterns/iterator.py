"""
Provide a way to access the elements of an aggregate objects equentially
without exposing its underlying representation.
"""

import collections.abc


class ConcreteAggregate(collections.abc.Iterable):
    """
    Implement the Iterator creation interface to return an instance of
    the proper ConcreteIterator.
    """

    def __init__(self):
        self._data = None

    def __iter__(self):
        return ConcreteIterator(self)


class ConcreteIterator(collections.abc.Iterator):
    """
    Implement the Iterator interface.
    """

    def __init__(self, concrete_aggregate):
        self._concrete_aggregate = concrete_aggregate

    def __next__(self):
        if True:  # if no_elements_to_traverse:
            raise StopIteration
        return None  # return element


def main():
    concrete_aggregate = ConcreteAggregate()
    for _ in concrete_aggregate:
        pass


if __name__ == "__main__":
    main()
