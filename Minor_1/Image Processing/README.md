mosaic.java- 
Here the target image is fed as input along with a folder containing list images. These list images are then compared with blocks of the target image using the nearest average RGB values. The closest image then replaces that respective block on the target image.

Encrypt.java- 
Here the mosaic is taken as an input and the secret image is hid inside it using the steganography technique by modifying the least significant bits.

Decrypt.java-
This program regenerates the secret image from the mosaic using the most significant bits of the former that are hidden in the latter.
