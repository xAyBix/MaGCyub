![Logo](src/main/resources/images/icon.png)

# MaGCyub (Magi-Cube)

`MaGCyub` is a personal encrypted storage system that compresses and encrypts files, then splits them into multiple uniformly sized encrypted blocks stored under random names.

The goal of `MaGCyub` is to provide a secure, opaque personal space where:

    - file contents are unreadable without a password

    - file names and structure reveal no meaning

    - data is evenly distributed across encrypted files
___
### Features

#### Password-based encryption
- All data is protected by a single user password. The password is never stored.

#### Compression + Encryption
- Files are compressed before encryption to reduce size and remove patterns.

#### Data splitting
- Encrypted data is split into multiple chunks of similar size.

#### Graph-based structure
- Encrypted chunks form a one-directional chain (or graph) starting from a root node.

#### Opaque storage
- Stored files:

    - have random names

    - contain no readable metadata

    - reveal no structure without the correct password
___
### High-Level Architecture

1- User provides:

        - Password

        - Root ID

2- The password is used to derive a master key.

3- A root container is decrypted using the master key.

4- The root reveals:

        - Metadata

        - The starting node ID

        - Mappings between logical node IDs and encrypted filenames

5- Each node:

        - Is encrypted independently

        - Contains:

            + Compressed content

            + A reference to the next node ID

        - Includes its own salt and nonce

6- Nodes are decrypted and reassembled in order to reconstruct the original data.

### Security Model (Important Notes)

- The password is never stored.

- Encryption security does NOT rely on obscurity:

        - Algorithms

        - File formats

        - Structure
    _may be publicly known._

- Without the correct password:

        - No file contents can be decrypted

        - Filenames and structure remain meaningless

- All cryptographic operations rely on well-established algorithms and libraries (no custom crypto).
