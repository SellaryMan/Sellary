import Link from "next/link";
const Sidebar = () => {
    return (
      <aside className="flex flex-col gap-2 sticky top-0 p-2 self-start h-full min-h-screen border-gray-200 border-r-[0.2px] shadow-xs shadow-right bg-gray-200">
        <Link href="/outbound">출고상품</Link>
        <Link href="/products">판매상품</Link>
      </aside>
    );
  };
  
  export default Sidebar;