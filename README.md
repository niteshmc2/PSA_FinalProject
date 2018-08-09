# PSA_FinalProject

# MNIST Image classifier

This program classifies the MNIST handwritten image dataset into the respective image classes, digits 0 to 9

### How to run?
  - Download the data from this source https://www.kaggle.com/lunarllama/mnist-dataset
  - Extract the archive and place train.csv in the root directory of the project and rename it to "data.csv"
  - Run the NeuralNet.java

### What are the inputs and outputs?
  - The data.csv has 42000 labelled images. This program uses the first 38000 images to train the neural net, and the remaining 4000 to validate the net.
  - The output can be seen in two places
-- A conusion matrix is exported out as confusionMatrix.csv into the root directory of the project   
-- Java IDE console - Prints out the expected output and the guessed output, along with all the activations of the output neurons 
``` 
Expected:4 -- Guessed:4 Array:Class-0:0.0 | Class-1:0.0 | Class-2:0.01 | Class-3:0.0 | Class-4:0.94 | Class-5:0.0 | Class-6:0.02 | Class-7:0.04 | Class-8:0.0 | Class-9:0.05 | 
```




