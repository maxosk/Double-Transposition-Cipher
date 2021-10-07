package com.company;


import java.util.*;


class Main {
    public static void main(String sap[]) {

        Scanner sc = new Scanner(System.in);

        System.out.print("\nПолучить шифровку или расшифровку? [ш/р]\n");
        String option = sc.nextLine();
        if (option.equals("ш")) {
            encrypt();
        } else if (option.equals("р"))
            decrypt();
        else
            System.out.print("Некорректный ввод");

    }

    static void encrypt() {
        Scanner sc = new Scanner(System.in);
        System.out.print("\nВведите сообщение для шифрования: ");
        String messageBefore = sc.nextLine();
        String message = messageBefore.trim().replaceAll(" ", "_");
        System.out.print("\nВведите ключ для столбцов: ");
        String keyСolumn = sc.next();
        System.out.print("\nВведите ключ для строк: ");
        String keyRow = sc.next();

        /* columnCount будет отслеживать столбцы кол-во столбцов*/
        int columnCount = keyСolumn.length();

        /* Количество строк будет отслеживаться rows...no строк = (длина открытого текста + длина ключа) / длина ключа */
        int rowCount = keyRow.length();
        /*Открытый текст и зашифрованный текст будут представлять собой массив, содержащий значения ASCII для соответствующих букв */
        int plainText[][] = new int[rowCount][columnCount];
        int cipherText[][] = new int[rowCount][columnCount];
        //System.out.println(message.length());
        /*Процесс шифрования*/
        System.out.print("\n-----Шифрование-----\n");
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
        //Проверка матрицы на пустые значения и замена пустого символа
        for (int f = 0; f < keyСolumn.length(); f++) {
            for (int g = 0; g < keyСolumn.length(); g++) {
                if ((int) plainText[f][g] == 0) {
                    plainText[f][g] = 95;
                }
            }

        }

        // Для хранения промежуточного результата

        /* здесь массив будет заполнен в соответствии с ключевым столбцом по столбцу */
        for (i = 0; i <columnCount  ; i++) {
            /*currentCol будет иметь номер текущего столбца, т.е.  в ключе будет сохранено значение ASCII, поэтому мы вычитаем его на 48, чтобы получить исходное число...и -1 будет вычитаться, так как позиция массива начинается с 0*/
            int currentCol = ((int) keyСolumn.charAt(i) - 48) - 1;
            for (j = 0; j < rowCount; j++) {
                cipherText[j][currentCol] = plainText[j][i];
            }

        }
       /* System.out.print("Зашифрованная таблица(читать по строкам): \n");
        for (i = 0; i < rowCount; i++) {
            for (j = 0; j < columnCount; j++) {
                System.out.print((char) cipherText[i][j] + "\t");
            }
            System.out.println();
        }*/


        int cipherTextColumn[][] = new int[rowCount][columnCount];

        /* здесь массив будет заполнен в соответствии со строкой */
        for (i = 0; i <rowCount ; i++) { //изменить счет позже
            //    currenRow будет иметь номер текущего столбца, т.е. должен быть read...as в ключе будет сохранено значение ASCII, поэтому мы вычитаем его на 48, чтобы получить исходное число...и -1 будет вычитаться, так как позиция массива начинается с 0
            int currenRow = ((int) keyRow.charAt(i) - 48) - 1;
            for (j = 0; j < columnCount; j++) {
                int a = cipherText[i][j];
                cipherTextColumn[currenRow][j] = a;
            }

        }


        System.out.print("Зашифрованная таблица(читать по строкам): \n");
        for (i = 0; i < rowCount; i++) {
            for (j = 0; j < columnCount; j++) {
                System.out.print((char) cipherTextColumn[i][j] + "\t");
            }
            System.out.println();
        }

        // return cipherTextColumn;

        String ct = "";
        for (int q =rowCount  - 1; q != -1; q--) {
            for (int z = 0; z < columnCount; z++) {
                if (cipherTextColumn[q][z] == 0)
                    ct = ct + 'x';
                else {
                    ct = ct + (char) cipherTextColumn[rowCount - 1 - q][z];
                }
            }
        }
        System.out.print("\nЗашифрованная строка: " + ct);
    }

    static void decrypt() {

        Scanner sc = new Scanner(System.in);
        System.out.print("\nВведите сообщение для расшифровки: ");
        String messageBefore = sc.nextLine();
        String message = messageBefore.trim().replaceAll(" ", "_");
        System.out.print("\nВведите ключ для столбцов: ");
        String keyСolumn = sc.next();
        System.out.print("\nВведите ключ для строк: ");
        String keyRow = sc.next();

        int columnCount = keyСolumn.length();

        /* Количество строк будет отслеживаться rows...no строк = (длина открытого текста + длина ключа) / длина ключа */
        int rowCount = keyRow.length();
        /*Открытый текст и зашифрованный текст будут представлять собой массив, содержащий значения ASCII для соответствующих букв */
        int plainText[][] = new int[rowCount][columnCount];
        int cipherText[][] = new int[rowCount][columnCount];
        //System.out.println(message.length());
        /*Процесс шифрования*/
        System.out.print("\n-----Расшифровка-----\n");

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


        int cipherTextColumn[][] = new int[rowCount][columnCount];

        for (i = 0; i <rowCount ; i++) { //изменить счет позже
            //    currenRow будет иметь номер текущего столбца, т.е. в ключе будет сохранено значение ASCII, поэтому мы вычитаем его на 48, чтобы получить исходное число...и -1 будет вычитаться, так как позиция массива начинается с 0
            int currenRow = ((int) keyRow.charAt(i) - 48) - 1;
            for (j = 0; j < columnCount; j++) {
                int a = plainText[currenRow][j];
                cipherTextColumn[i][j] = a;
            }

        }

        /*System.out.print("Расшифрованная таблица(читать по строка): \n");
        for (i = 0; i < rowCount; i++) {
            for (j = 0; j < columnCount; j++) {
                System.out.print((char) cipherTextColumn[i][j] + "\t");
            }
            System.out.println();
        }*/


        for (i = 0; i <columnCount  ; i++) {
            int currentCol = ((int) keyСolumn.charAt(i) - 48) - 1;
            for (j = 0; j < rowCount; j++) {
                plainText[j][i] = cipherTextColumn[j][currentCol];
            }
        }

        System.out.print("Расшифрованная таблица(читать по строка): \n");
        for (i = 0; i < rowCount; i++) {
            for (j = 0; j < columnCount; j++) {
                System.out.print((char) plainText[i][j] + "\t");
            }
            System.out.println();
        }


        // Итоговое слово
        String pt = "";
        for (int w = rowCount - 1; w != -1; w--) {
            for (int r = 0; r < columnCount; r++) {
                if (plainText[w][r] == 0)
                    pt = pt + "";
                else {
                    pt = pt + (char) plainText[rowCount - 1 - w][r];
                }
            }
        }
        System.out.print("\nРасшифрованная строка: " + pt);

        System.out.println();
    }
}