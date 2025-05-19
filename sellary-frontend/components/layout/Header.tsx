import Link from "next/link";
const Header=()=>{
    return(
        <header className="flex items-center justify-between text-white p-3 w-full">
            {/* 왼쪽 영역 */}
            <div className="">
                {/* 로고 */}
                {/* <Image src="" alt="logo"/> */}
                <Link href="/">SELLARY</Link>
            </div>
            {/* 가운데 영역 */}
            <div className="justify-center"></div>
            {/* 오른쪽 영역 */}
            <div className="justify-end "></div>
        </header>
    )
}
export default Header;