# ImpactNumberSummarizer

## Overview

The **Number Range Summarizer** is a simple program designed to collect and summarize a range of integers from various input sources, including user input, file input, and predefined test cases. It processes comma-separated integers, handles duplicates, and summarizes them into consecutive ranges.

### Features:
- Collects integers from user input or a text file.
- Removes duplicate values automatically.
- Summarizes the collection of integers into ranges (e.g., "1-3, 6-8").
- Interactive interface for testing with sample or custom inputs.

## Installation

1. Clone this repository to your local machine:
    ```bash
    git clone https://github.com/bobbyfang/number-range-summarizer.git
    ```

2. Navigate to the project directory:
    ```bash
    cd number-range-summarizer
    ```

3. Build the project using Gradle:
    - For Gradle:
      ```bash
      gradle build
      ```

4. Ensure the `testInputs.txt` file is located in the `resources` folder for file input testing.

## Usage

### Running the Program

To run the program, execute the `main` method. The program will present a menu with the following options:

1. **Provided sample case**: Runs a predefined test case of comma-separated integers.
2. **Provide custom test case**: Allows the user to input a custom list of integers, which will be processed and summarized.
3. **Read multiple from text file**: Reads test cases from the `testInputs.txt` file located in the `resources` folder and processes them.
0. **Exit**: Exits the program.
