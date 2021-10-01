package com.company;


import java.util.*;


class Main {
    public static void main(String sap[]) {
        Scanner sc = new Scanner(System.in);

        System.out.print("\nВведите сообщение для (де)шифрования: ");
        String messageBefore = sc.nextLine();
       String message = messageBefore.trim().replaceAll(" ", "_");
        System.out.print("\nВведите ключ для столбцов: ");
        String keyСolumn = sc.next();
        System.out.print("\nВведите ключ для строк: ");
        String keyRow = sc.next();

        /* columnCount будет отслеживать столбцы кол-во столбцов*/
        int columnCount = keyСolumn.length();

        /* Количество строк будет отслеживаться rows...no строк = (длина открытого текста + длина ключа) / длина ключа */
        int rowCount = columnCount;
       // int rowCount = (message.length() + columnCount) / columnCount;
        int rowCount1 = message.length();
        /*Открытый текст и зашифрованный текст будут представлять собой массив, содержащий значения ASCII для соответствующих букв */
        int plainText[][] = new int[rowCount][columnCount];
        int cipherText[][] = new int[rowCount][columnCount];
        //System.out.println(message.length());
        /*Процесс шифрования*/
        System.out.print("\n-----Шифрование-----\n");
        cipherText = encrypt(plainText, cipherText, message, rowCount, columnCount, keyСolumn,keyRow);

        // Итоговое слово
        String ct = "";
        for (int i = columnCount-1; i!=-1; i--) {
            for (int j = 0; j < rowCount; j++) {
                if (cipherText[i][j] == 0)
                    ct = ct + 'x';
                else {
                    ct = ct + (char) cipherText[columnCount-1-i][j];
                }
            }
        }
        System.out.print("\nЗашифрованная строка: " + ct);

        /*Процесс дешифровки*/
        System.out.print("\n\n\n-----Расшифровка-----\n");

        plainText = decrypt(plainText, cipherText, ct, rowCount, columnCount, keyСolumn,keyRow);

        // Итоговое слово
        String pt = "";
        for (int i = columnCount-1; i!=-1; i--) {
            for (int j = 0; j < columnCount; j++) {
                if (plainText[i][j] == 0)
                    pt = pt + "";
                else {
                    pt = pt + (char) plainText[columnCount-1-i][j];
                }
            }
        }
        System.out.print("\nРасшифрованная строка: " + pt);

        System.out.println();
    }

    static int[][] encrypt(int plainText[][], int cipherText[][], String message, int rowCount, int columnCount, String keyColumn, String keyRow) {
        int i, j;
        int k = 0;

        /* здесь массив будет заполняться строка за строкой  */
        for (i = 0; i < rowCount; i++) {
            for (j = 0; j < columnCount; j++) {
                /* завершение condition... длина строки может быть меньше, чем 2-D массив */
                if (k < message.length()) {
                    /* будут размещены соответствующие символы ASCII*/
                    plainText[i][j] = (int) message.charAt(k);
                    k++;
                } else {
                    break;
                }
            }
        }
        // Для хранения промежуточного результата

        /* здесь массив будет заполнен в соответствии с ключевым столбцом по столбцу */
        for (i = 0; i < columnCount; i++) {
            /*currentCol будет иметь номер текущего столбца, т.е.  в ключе будет сохранено значение ASCII, поэтому мы вычитаем его на 48, чтобы получить исходное число...и -1 будет вычитаться, так как позиция массива начинается с 0*/
            int currentCol = ((int) keyColumn.charAt(i) - 48) - 1;
            for (j = 0; j < rowCount; j++) {
                cipherText[j][i] = plainText[j][currentCol];
            }

        }
      //  int cipherTextColumn[][] ;
        //cipherTextColumn= cipherText.clone();
//
//        System.out.println(cipherTextColumn);
//        /* здесь массив будет заполнен в соответствии со строкой */
//        for (i = 0; i < columnCount; i++) { //изменить счет позже
//            //    currenRow будет иметь номер текущего столбца, т.е. должен быть read...as в ключе будет сохранено значение ASCII, поэтому мы вычитаем его на 48, чтобы получить исходное число...и -1 будет вычитаться, так как позиция массива начинается с 0
//            int currenRow = ((int) keyRow.charAt(i) - 48) - 1;
//            for (j = 0; j < rowCount; j++) {
//                cipherText[i][j] =  cipherTextColumn[currenRow][j];
//
//                System.out.print((char) cipherTextColumn[currenRow][j]);
//            }
//
//        }

        int cipherTextColumn[][] = new int[rowCount][rowCount];

        /* здесь массив будет заполнен в соответствии со строкой */
        for (i = 0; i < columnCount; i++) { //изменить счет позже
            //    currenRow будет иметь номер текущего столбца, т.е. должен быть read...as в ключе будет сохранено значение ASCII, поэтому мы вычитаем его на 48, чтобы получить исходное число...и -1 будет вычитаться, так как позиция массива начинается с 0
            int currenRow = ((int) keyRow.charAt(i) - 48) - 1;
            for (j = 0; j < rowCount; j++) {
                cipherTextColumn[i][j]=cipherText[currenRow][j];
            }

        }


        System.out.print("Зашифрованная таблица(читать по строкам): \n");
        for (i = 0; i < rowCount; i++) {
            for (j = 0; j < columnCount; j++) {
                System.out.print((char) cipherTextColumn[i][j] + "\t");
            }
            System.out.println();
        }

        return cipherTextColumn;
    }

    static int[][] decrypt(int plainText[][], int cipherText[][], String message, int rowCount, int columnCount, String key,String keyRow) {
        int i, j;
        int k = 0;

        int cipherTextColumn[][] = new int[rowCount][rowCount];

        for (i = 0; i < columnCount; i++) { //изменить счет позже
            //    currenRow будет иметь номер текущего столбца, т.е. в ключе будет сохранено значение ASCII, поэтому мы вычитаем его на 48, чтобы получить исходное число...и -1 будет вычитаться, так как позиция массива начинается с 0
            int currenRow = ((int) keyRow.charAt(i) - 48) - 1;
            for (j = 0; j < rowCount; j++) {
                int a= cipherText[i][j];
                cipherTextColumn[currenRow][j]=a;
            }

        }



        for (i = 0; i < columnCount; i++) {
            int currentCol = ((int) key.charAt(i) - 48) - 1;
            for (j = 0; j < rowCount; j++) {
                plainText[j][currentCol] = cipherText[j][i];
            }
        }

        System.out.print("Расшифрованная таблица(читать по строка): \n");
        for (i = 0; i < rowCount; i++) {
            for (j = 0; j < columnCount; j++) {
                System.out.print((char) plainText[i][j] + "\t");
            }
            System.out.println();
        }

        return plainText;
    }
}
