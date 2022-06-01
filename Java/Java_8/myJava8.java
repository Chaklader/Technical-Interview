import java.util.*;
import java.io.*;
import java.util.function.*;

import java.lang.annotation.ElementType;
import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

class RepeatingAnnotations {


    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    public @interface Filters {

        Filter[] value();
    }
    
    @Target( ElementType.TYPE )
    @Retention( RetentionPolicy.RUNTIME )
    @Repeatable( Filters.class )
    public @interface Filter {

        String value();
    };
    
    @Filter( "filter1" )
    @Filter( "filter2" )
    public interface Filterable {        
    }
    
    public static void main(String[] args) {
        for( Filter filter: Filterable.class.getAnnotationsByType( Filter.class ) ) {
            System.out.println( filter.value() );
        }
    }
}


class Car {

    public static Car create( final Supplier< Car > supplier ) {
        return supplier.get();
    }              
        
    public static void collide( final Car car ) {
        System.out.println( "Collided " + car.toString() );
    }
        
    public void follow( final Car another ) {
        System.out.println( "Following the " + another.toString() );
    }
        
    public void repair() {   
        System.out.println( "Repaired " + this.toString() );
    }
}

public class myJava8 {


	public static void test( ) {

		// Create a Function from a lambda expression.
		// ... It returns the argument multiplied by two.
		Function<Integer, Integer> func = x -> x * 2;

		// Apply the function to an argument of 10.
		int result = func.apply(10);
		System.out.println(result);
    }


	private interface DefaulableFactory {

	    // Interfaces now allow static methods
	    static Defaulable create( Supplier< Defaulable > supplier ) {
	        return supplier.get();
	    }
	}

	private interface Defaulable {
    // Interfaces now allow default methods, the implementer may or 
    // may not implement (override) them.
	   
	    default String notRequired() { 

	        return "Default implementation"; 
	    }        
	}
	        
	private static class DefaultableImpl implements Defaulable {

	}
	    
	private static class OverridableImpl implements Defaulable {
	    
	    @Override
	    public String notRequired() {

	        return "Overridden implementation";
	    }
	}


	@FunctionalInterface
	public interface FunctionalDefaultMethods {

	    void method();
	        
	    default void defaultMethod() {            
	    }        
	}

	@FunctionalInterface
	public interface Functional {

	    void method();
	}

	public static void myCity (){

		Arrays.asList( "a", "b", "d" ).forEach( e -> System.out.println( e ) );
		Arrays.asList( "a", "b", "d" ).forEach( ( String e ) -> System.out.println( e ) );

		Arrays.asList( "a", "b", "d" ).forEach( e -> {

		    System.out.print( e );
		    System.out.print( e );
		} );


		String separator1 = ",";

		Arrays.asList( "a", "b", "d" ).forEach( 
		    ( String e ) -> System.out.print( e + separator1 ) );

		final String separator2 = ",";
		Arrays.asList( "a", "b", "d" ).forEach( 
		    ( String e ) -> System.out.print( e + separator2 ) );

		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> e1.compareTo( e2 ) );

		Arrays.asList( "a", "b", "d" ).sort( ( e1, e2 ) -> {
		    int result = e1.compareTo( e2 );
		    return result;
		} );	
	}


	public static void learn8(){

		Integer[]  boxedint = {12,45,11,56};
		int [] unboxing = Stream.of(boxedint).mapToInt(Integer::intValue).toArray();

		int[] arr = {12,4,67,89};
		Integer[] boxed =  Arrays.stream(arr).boxed().toArray(Integer[]:: new );

	}


	public static void learn81(){

		List<Integer> numbers = Arrays.asList(3,2,4,67);
		System.out.println(numbers);

		String nums = numbers.stream().filter(n -> n > 4)
							.map(n->n+"d")
							.collect(Collectors.joining(","));
		
		// 67d
		System.out.println(nums);
	}


	public static void main(String[] args) {

		System.out.println("Seattle");
		// myCity();

		Defaulable defaulable = DefaulableFactory.create( DefaultableImpl::new );
	    System.out.println( defaulable.notRequired() );
		    
	    defaulable = DefaulableFactory.create( OverridableImpl::new );
	    System.out.println( defaulable.notRequired() );


	    // car impelmentation 
	    final Car car = Car.create( Car::new );
		final List< Car > cars = Arrays.asList( car );
		cars.forEach( Car::collide );
		cars.forEach( Car::repair );

		final Car police = Car.create( Car::new );
		cars.forEach( police::follow );

	}
	
}