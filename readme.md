# Belajar Java Thread
source : Programmer Zaman Now


### Concurrency vs Parallel
- Concurrency artinya mengerjakan beberapa pekerjaan satu persatu pada satu waktu
- Parallel artiya mengerjakan beberapa pekerjaan sekaligus pada satu waktu
- Browser akan melakukan proses concurrent ketika membuka web, browser akan melakukan http request, lalu mendownload semua file web (html, css, js) lalu merender dalam bentuk tampilan web
- Browser akan melakukan proses parallel, ketika kita membuka beberapa tab web, dan juga sambil download beberapa file, dan menonton video dari web streaming 

### Main Thread 
- Secara default, saat sebuah aplikasi Java berjalan, minimal akan ada satu thread yang berjalan
- Dalam aplikasi Java biasa, biasanya kode program kita akan berjalan di dalam thread yang bernama main


### Thread sleep
- Pada saat proses development, kada kita butuh melakukan simulasi proses yang berjalan dalam waktu tertentu


### Thread Join
- menunggu sampai sebuah thread selesai

### Thread Interrupt
- Interrupt merupakan mengirim sinyal ke thread bahwa thread tersebut harus berhenti melakukan pekerjaannya saat ini
- Untuk melakukan interrupt, kita bisa menggunakan method interrupt() pada thread
- Saat kita memanggil method interrupt(), secara otomatis Thread.interrupted() akan bernilai true
- kode program kita pada Runnable harus melakukan pengecekan interrupted, jika tidak, sinyal interrupt tidak ada gunanya


### Thread name
- Thread name secara default akan menggunakan nama Thread-{counter}
- Namun kita juga bisa mengubahnya dengan menggunakan method setName(name), dan getName() untuk mendapatkan nama thread nya

### Thread state
- status sebuah thread


### Thread daemon
- Jika kita mengubah thread menjadi daemon thread, menggunakan setDaemon(true), maka secara otomatis thread tersebut menjadi daemon thread
- Daemon thread tidak akan ditunggu jika memang program Java akan berhenti


### Race Condition
- keadaan ketika sebuah data diubah secara bersamaan oleh beberapa thread sekaligus

### synchronization 
- Masalah race condition bisa diselesaikan dengan synchronization
- synchronization merupakan fitur yg memaksa kode program hanya boleh diakses dan dieksekusi oleh 1 thread saja
- hal ini menyebabkan thread lain yang akan mengakses kode tersebut harus menunggu thread yg lebih dulu mengakses.
- sehingga proses synchronization ini lebih lambat 
- namun proses synchronization lebih aman karena tidak akan terjadi race condition


#### synchronized method
- menambahkan kata kunci thread pada method

##### Intrinsic Lock
- synchronization sebenarnya menggunakan lock
  - ketika kita menggunakan synchronized method, secara otomatis java akan membuat intrinsic lock atau monitor lock
  - ketika synchronized method dipanggil oleh thread, thread akan mencoba mendapatkan intrinsic lock,
  - setelah method selesai(sukses/error) maka thread akan mengembalikan intrinsic lock
  - jika menggunakan synchronized method, intrinsic lock otomatis dihandle oleh java

#### synchronized statement
 - saat kita menggunakan synchronized method, secara otomatis selutuh method akan ter synchronization
 - jika kita ingin menggunakan synchronization pada kode tertentu, kita bisa menggunakan synchronized statement
 - jika menggunakan synchronized statement, intrinsic lock harus dihandle manual

### Deadlock
- Kondisi dimana beberapa thread saling menunggu satu sama lain.
- Biasanya terjadi karena thread tersebut melakukan lock dan menunggu lock dari thread lain
- thread a lock x dan menunggu unlock y
- thread b lock y dan menunggu unlock x
- Masalah deadlock harus diselesaikan sendiri oleh programmer yang membuat kode program nya



### Thread Communication
- dalam multithreading programming, kadang kita membutuhkan sebuah thread yang perlu menunggu thread lain menyelesaikan tugas tertentu.
- tidak ada cara otomatis komunikasi antar thread secara langsung
- programmer harus melakukannya secara manual

#### Manual
- menggunakan looping (tidak direkomendasikan) 
- menggunakanBuang-buang resource CPU dan juga jika terjadi interrupt, loop akan terus berjalan tanpa henti

#### Wait and Notify
- Java sudah menyediakan solusi yang lebih baik untuk thread communication dengan menambahkan method wait dan notify di java.lang.Object
- kita bisa membuat object apapun menjadi lock dan menggunakan wait() untuk menunggu
- dan menggunakan notify() untuk memberitahu data sudah tersedia
- notify() akan memberi tahu thread lain yang sedang melakukan wait() bahwa proses bisa dilanjutkan


### Timer
- Timer merupakan class untuk memfasilitasi eksekusi job secara asynchronous di masa depan
- Timer bisa di schedule untuk berjalan satu kali (delayed job), atau bisa berjalan berulang kali (repeated job)


## Management Thread

- Thread merupakan object yang sangat 'mahal' . pembuatan object thread bisa memakan memory 512kb - 1mb
- Jika kita tidak mengatur pembuatan thread dengan baik, bisa jadi aplikasi kita akan kehabisan memori
- pembuatan thread secara manual tidak disarankan. di java modern sekarang, disarankan menggunakan thread pool
- 
### Thread pool
- Threadpool merupakan class yang digunakan untuk management thread
- kita tidak perlu membuat thread secara manual
- thread pool bisa melakukan reusable thread yg sudah selesai bekerja
- Threadpool di Java direpresentasikan dalam class bernama ThreadPoolExecutor

#### pengaturan thread pool
- core pool -> minimal thread yang akan dibuat ketika thread pool dibuat
- max pool -> maximal thread yang bisa dibuat
- keep alive time -> berapa lama thread akan dihapus jika tidak bekerja
- queue -> antrian untuk menampung pekerjaan yang dikirim ke queue


### eksekusi runnable
- menggunakan method execute() milik threadpool
- Secara otomatis data runnable akan dikirim ke queue threadpool untuk dieksekusi oleh thread yang terdapat di threadpool


### MEnghentikan thread pool
- Jika kita sudah selesai menggunakan threadpool, dan tidak akan menggunakannya lagi, ada baiknya kita hentikan dan matikan ThreadPool nya
- Caranya kita bisa menggunakan method shutdown() untuk menghentikan threadpool, jika ada pekerjaan yang belum dikerjakan, maka akan di ignore
- Atau gunakan shutdownNow() untuk menghentikan threadpool, namun pekerjaan yang belum dikerjakan akan dikembalikan
- Atau jika kita ingin menunggu sampai threadpool selesai, kita bisa gunakan awaitTermination()


### Rejected Handler
- Jika queue penuh dan semua thread juga sedang bekerja, maka secara otomatis akan dihanlde oleh object RejectedExecutionHandler
- secara default, implementasi rejected handler ini akan mengembalikan exception RejectedExecutionException ketika kita submit runnable
- Kita bisa membuat rejected handler sendiri


### Executor Service
- ThreadPoolExecutor merupakan implementasi dari interface executor dan ExecutorService
- Dalam kebayakan kasus, jarang sekali membuat ThreadPool secara manual, biasanya untuk mengeksekusi runnable kita akan menggunakan Executor Service


### Executors
- ExecutorService adalah interface, salah satu implementasinya adalah ThreadPoolExecutor
- namun, ada yang lebih mudah yaitu menggunakan class Executors
- Jumlah queue tidak terbatas

#### Executors Method
- newFixedThreadPool(n) -> membuat thread pool dengan jumlah min dan max fixed berupa n
- newSingleThreadExecutors() -> Membuat thread pool dengan jumlah min dan max 1
- newCacheThreadPool() -> membuat thread pool dengan jumlah thread yang bisa bertambah sampai tak hingga



### Callable
- jika kita menggunakan runnable, maka kita tidak dapat mendapatkan data return karena runnable adalah void
- Callable mirip seperti runable, bedanya callable dapat mengembalikan data
- Callable bertipe generic, sehingga kita dapat menentukan tipe returnnya
- kita bisa menggunakan method submit() pada executorService untuk mengeksekusi callable, dan akan memberikan return Future<T>


### Future
- Future merupakan representasi data yang akan dikembalikan oleh proses asynchronous
- Menggunakan Future, kita bisa mengecek apakah pekerjaan Callable sudah selesai atau belum, dan juga mendapat data hasil dari Callable

