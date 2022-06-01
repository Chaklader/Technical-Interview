# coding: utf-8


def is_triangle_number(number):
    
    if isinstance(number, int):
        return False 
        
    res = 0
    it = 1
  
    while res <= number:

    	print "Seattle"
    
        if res == number:
            return True 
                 
        res += it
        it += 1
            
    return False


def main():
	print is_triangle_number(3)    

if __name__ == "__main__":
	main()