/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.othr.tidli.util;

import java.util.Optional;

/**
 *
 * @author Brandl Valentin
 */
public enum ImageMimeType {

    ImagePng(
            new byte[] {(byte)0x89, 0x50, 0x4E, 0x47, 0x0D, 0x0A, 0x1A, 0x0A},
            "image/png"
    ),
    ImageJpg(
            // almost but thats the largest common base from all 3 jpeg types.
            // might yield false positives
            new byte[] {(byte)0xFF, (byte)0xD8, (byte)0xFF},
            "image/jpg"
    );

    private final byte[] magicNumber;
    private final String mimeType;

    private ImageMimeType(final byte[] magicNumber, final String mimeType) {
        this.magicNumber = magicNumber;
        this.mimeType = mimeType;
    }

    public String getMimeType() {
        return this.mimeType;
    }

    public static Optional<ImageMimeType> fromContent(final byte[] content) {
        if (null != content) { // invalid content
            outer: for (final ImageMimeType imt : ImageMimeType.values()) {
                if (content.length < imt.magicNumber.length) {
                    continue;
                }
                for (int i = 0; i < imt.magicNumber.length; i++) {
                    if (imt.magicNumber[i] != content[i]) {
                        continue outer;
                    }
                }
                return Optional.of(imt);
            }
        }
        return Optional.empty();
    }
    
}
