'''
    Author: Dominic MacIsaac
    Feel free to add more tests to this file to ensure your code is working properly.
    To run
        - place this file in the same directory as the java file
        - In terminal, move to the directory containing the java file and this file
        - Run the command: python3 prog2_student_test.py
'''

import subprocess
import random

def write_array_to_file(array, filename):
    """
    Writes each item of an array to a new line in a file named filename.
    Inputs:
        array: List of items to write to the file.
        filename: Name of the file to write to.
    Outputs:
        None
    """
    with open(filename, 'w') as file:
        for item in array:
            file.write(str(item) + '\n')


def compile_java_code_indir(java_file_name):
    """Compiles the Java code and returns True if successful, False otherwise."""
    try:
        subprocess.check_output(["javac", java_file_name], stderr=subprocess.STDOUT)
        return True
    except subprocess.CalledProcessError:
        return False

def generate_random_numbers(x: int, start: int = 0, end: int = 100) -> [int]:
    """
    Generate a list of x random numbers between a specified range.
    
    Args:
    x (int): The number of random numbers to generate.
    start (int): The start of the range (inclusive).
    end (int): The end of the range (inclusive).
    
    Returns:
    List[int]: A list containing x random numbers.
    """
    random.seed(0)
    return [random.randint(start, end) for _ in range(x)]

def run_command(command_list):
    timeout = 10
    try:
        # Run the command and wait for it to complete
        output = subprocess.check_output(command_list, timeout=timeout, stderr=subprocess.STDOUT)
        return output.decode('utf-8').strip()
    except subprocess.TimeoutExpired:
        # Return a message if the process times out
        print("TIMEOUT")
        return f"Function timed out after {timeout} seconds"
    except subprocess.CalledProcessError as e:
        # Return the error output if the command fails
        return e.output.decode('utf-8').strip()
    except Exception as e:
        # General exception handling
        return str(e)

def read_numbers_from_file(file_name: str) ->[int]:
    """
    Read numbers from a file where each number is on a new line and return a list of numbers.
    
    Args:
    file_name (str): The name of the file to read from.
    
    Returns:
    List[int]: A list of numbers read from the file.
    """
    numbers = []
    with open(file_name, 'r') as file:
        for line in file:
            # Attempt to convert each line into an integer and append to the list
            try:
                number = int(line.strip())
                numbers.append(number)
            except ValueError:
                # Skip lines that cannot be converted to an integer
                continue
    
    return numbers

def run_test(test_file_name,input_nums):
    print(f"Test 1 (java Richest {test_file_name}):  ", end='')

    # run the java program
    run_command(['java', 'Richest', test_file_name]).splitlines()

    # read the results from the files into lists
    results_10k = read_numbers_from_file('richest-top10k.output')
    results_10 = read_numbers_from_file('richest-top10.output')

    # sort the input list in descending order
    random_nums_sorted = sorted(input_nums, reverse=True)

    # check the results
    if results_10k != random_nums_sorted[:10000]:
        print("Test failed: error in 10k output")
        return
    if results_10 != random_nums_sorted[:10]:
        print("Test failed: error in 10 output")
        return
    
    print("Both tests passed")
    


if __name__ == '__main__':
    # Compile the java code
    print("Compile: ", str(compile_java_code_indir('Richest.java')))

    # Generate a test file with 15000 random numbers
    test_file_name = 'test_input_file.txt'
    random_nums = generate_random_numbers(15000,0,1000000)
    write_array_to_file(random_nums, test_file_name)

    # Run the test
    run_test(test_file_name, random_nums)
    
    
    


