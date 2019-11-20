# Lora Codecs
Master Build CI Status: [![CircleCI](https://circleci.com/gh/davidparry/lora-codecs/tree/master.svg?style=svg)](https://circleci.com/gh/davidparry/lora-codecs/tree/master)

# Purpose - Goal
A single utility jar to use in a Java project to encode and decode LoRaWan Manufactures Payloads.

# Driving Architecture
Keep it simple - since it only aims to do one thing well it has no dependencies on other third party libraries.
Memory footprint - you will see that most of the classes and data is static. This choice is the nature of the device it self 
where updates to how it encodes or decodes the data is completed when manufacturing is done. This is good for us since all we really need to do is define the encoding and decoding in a class that represents the sensor. 
Updates - These sensors are not updated they will last up to 10 years in most cases, so instead we would have new versions and at that time if a new way of encode/decode takes place it will be published.

#Little info on LoRaWan
LoRaWan is a Specification is a Low Power Wide Area (LPWA) network protocol designed to wirelessly connect battery operated ‘things’ to the internet in regional, national or global networks. The protocol includes features that support low-cost, mobile, and secure bi-directional communication for Internet of Things (IoT).
Link: https://lora-alliance.org/lorawan-for-developers

