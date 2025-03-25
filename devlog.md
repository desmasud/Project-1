# 3-8-2025 7:00 AM
## what do I know
There will be three separate program that works togethe:
    - logger: logs all activity
    - Encryption: Encrypting and decrypting of string
    - driver: the main program that facilitate the interaction of the other two programm. It serves as an OS.

## My overal plan
    - use Java as my main language
    - search how to use process class in Java
    - learn how to encrypt and decrypt string in java
    - research how inter-process communication work and how to develop an app that serve as a driver that facilitate it.
## Today I will work on logger program
    - It will write log messege and write it to log file
    - It will start from the non-white char
    - It will accept message from a command line by entering the name of the log file.
    - It will work Until it recieves 'QUIT'.

# 3-10-2025 10:43 AM
##  So far
    - I have developped the logger part of the project and read about how Vigenère cypher work and what and how it works.
## today's goal
    - To develop encryptiion program
        - write a code to accept a request through command line from the user
        - It will start from the non whitspace letter
        Commmands
            - PASS to set the current passkey to use when encrypting or decrypting
            - ENCYPT to encrypt the current passkey with Vigenère cypher to encrypt the argument and the output.
            - Decrypt to use current passkey along with Vigenère cypher to decrypt the argument
            - All three argument should have error output if passkey is not set
            - QUIT to exit the program
    - And start reserach about how to create Driver program and how it work in Java.

# 3-10-2025 5:30pm
    - I have learnt how to implement Vigenère cypher by shifting words according to the letters the user used as passkey.
    - some problem I encountered  how to return the the result message after the encryption is done. 
    - I have tested the code and it seems work well. I need to develop the driver program and see it works perfectrly as intended and make the necessary adjustment accordint to it.


# 3-11-2025 10:15am
    - so far I have successfully run both logger and encryption program independently. 
    - Today I am going to learn how interprocess work and how to open two processor at the same time and how to able to make them connect them by using driver program.
    - I need to research how pipes work and how to use them in java.
    - How to make the driver as the main operater for both logger and encryption.
    - I will try to program the pipe part of the program and test it.
# 3-20-2025 8:15 AM
    - I have been reading how to use pipe to make two programs communicate each othe.
    - I have been reading about the process class and how to start other programs based on using the process class.
    - I have developed some part of the driver program that will start the logger and encryption and communicate with them.
    - The process class in java used to create and manage system process. It allows a java program to start external programs, communicate with them through commandline and monitor it. It serve as OS.
# 3-22-2025 9:50
    - I have been devloped the three program and tested them. I have some java.Lang error. I am planning to fix it and propare for the submition.
    - The Driver program starts both the logger and the encryption program.
    - It has java.lang problem when I try to excute the instruciton and try to fix it.


# 3-25-2025
   - I will be able to finished the driver program. the challenging was handling the command line from the driver to the encryption. 
   - I have researched and tried to fix it.
   - I have learnt how to use pipe to communicate two programmers each othe in java
